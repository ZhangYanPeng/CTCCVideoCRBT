package com.CTCC.CRBT.Service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.CTCC.CRBT.Common.Constant;
import com.CTCC.CRBT.DAO.PageResults;
import com.CTCC.CRBT.DAO.Impl.VideoDAOImpl;
import com.CTCC.CRBT.Entity.Video;
import com.CTCC.CRBT.Service.IVideoService;

import org.apache.commons.net.ftp.FTPClient;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

@Service
public class VideoServiceImpl implements IVideoService {
	@Autowired
	VideoDAOImpl videoDAO;

	@Override
	@Transactional
	public PageResults<Video> GetByPage(int pageNo, String findStr) {
		// TODO Auto-generated method stub
		System.err.println(pageNo);
		System.err.println(findStr);
		try {
			String hql = "from Video";
			String countHql = "select COUNT(*) from Video";
			if (findStr == null || findStr.equals("")) {
				Object[] params = {};
				return videoDAO.findPageByFetchedHql(hql, countHql, pageNo, Constant.PAGESIZE, params);
			} else {
				hql += " where video_name like ?";
				countHql += " where video_name like ?";
				Object[] params = { "%" + findStr + "%" };
				return videoDAO.findPageByFetchedHql(hql, countHql, pageNo, Constant.PAGESIZE, params);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	@Transactional
	public Video getByName(String name) {
		// TODO Auto-generated method stub
		try {
			String hql = "from Video where video_name = ?";
			Object[] params = { name };
			return videoDAO.getByHQL(hql, params);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	@Transactional
	public int Add(Video video) {
		// TODO Auto-generated method stub
		try {
			videoDAO.saveOrUpdate(video);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		return 1;

	}

	@Override
	@Transactional
	public Video validVideoName(String video_name) {
		// TODO Auto-generated method stub
		try {
			String hql = "from Video where video_name = ?";
			Object[] objs = { video_name };
			return videoDAO.getByHQL(hql, objs);
		} catch (Exception e) {
			return null;
		}
	}

	// 上传文件到远程FTP文件服务器
	private static final Logger logger = LoggerFactory.getLogger(VideoServiceImpl.class);
	private static FTPClient ftpClient;

//	@Override
//	// 外部调用方法
//	public boolean uploadFile(File file) {
//		logger.info("开始连接ftp服务器！");
//		// 当天的年月日作为目录名称
//		DateTime dateTime = new DateTime(new Date());
//		String catalogue = dateTime.toString(Constant.STANDARD_FORMAT);		
//		boolean result=false;
//		try {
//			result = uploadFile(catalogue, file);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		logger.info("开始连接ftp服务器,结束上传,上传结果:{}", result);
//		return result;
//	}

//	//视频上传FTP文件服务器：测试
//	public static void main(String[] args) throws Exception {
//		File file = new File("E:\\2019624.mp4");
//		this.uploadFile(file);
//	}

	@Override
	@Transactional
	// 外部调用方法
	public String uploadFile(MultipartFile file, String path) {
		logger.info("开始连接ftp服务器！");
		// 当天的年月日作为目录名称
		DateTime dateTime = new DateTime(new Date());
		String catalogue = dateTime.toString(Constant.STANDARD_FORMAT);

		logger.info("文件上传的绝对地址：{}", path);
		// 获取文件名
		String fileName = file.getOriginalFilename();
		// 获取文件名的扩展名
		String fileExtensionName = fileName.substring(fileName.lastIndexOf(".") + 1);
		// 创建一个新名称，等下用于上传文件的名称
		String uploadFileName = UUID.randomUUID().toString() + "." + fileExtensionName;
		logger.info("开始上传文件,上传文件的文件名:{},新文件名:{}", fileName, uploadFileName);
		String uploadFileUrl = catalogue + "/" + uploadFileName;
		logger.info("文件上传后对应的FTP文件服务器地址:{}", uploadFileUrl);

		File fileDir = new File(path);
		// 如果目录不存在，则创建该目录
		if (!fileDir.exists()) {
			// 获取权限
			fileDir.setWritable(true);
			// 创建目录
			fileDir.mkdirs();
		}
		boolean result = false;
		// 把上传路径和新文件名传入新targetFile
		File targetFile = new File(path, uploadFileName);
		try {
			// 文件写入传入的目录中
			file.transferTo(targetFile);
			// 把写入的文件上传到ftp服务器中
			result = uploadFile(catalogue, targetFile);
			// 删掉tomcat中的文件
			//targetFile.delete();
		} catch (IOException e) {
			logger.error("上传文件异常", e);
		}
		logger.info("开始连接ftp服务器,结束上传,上传结果:{}", result);
		return uploadFileUrl;
	}

	// 内部调用方法
	private static boolean uploadFile(String remotePath, File file) throws IOException {
		boolean uploaded = true;
		FileInputStream fis = null;
		if (connectServer(Constant.ftpIp, Constant.ftpPort, Constant.ftpUser, Constant.ftpPwd)) {
			try {
				// 判断这个目录是否存在，不存在则进行创建
				ftpClient.makeDirectory(remotePath);
				// 切换到remodePath目录
				ftpClient.changeWorkingDirectory(remotePath);
				// 设置1m缓冲
				ftpClient.setBufferSize(1024);
				// 设置编码
				ftpClient.setControlEncoding("UTF-8");
				// 设置文件类型：二进制文件
				ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
				// 被动模式
				ftpClient.enterLocalPassiveMode();
				fis = new FileInputStream(file);
				ftpClient.storeFile(file.getName(), fis);
			} catch (IOException e) {
				logger.error("上传文件异常", e);
				uploaded = false;
			} finally {
				fis.close();
				ftpClient.disconnect();
			}
		}
		return uploaded;
	}

	// 连接FTP文件服务器
	private static boolean connectServer(String ip, int port, String user, String pwd) {
		boolean isSuccess = false;
		ftpClient = new FTPClient();
		try {
			ftpClient.connect(ip, port);
			isSuccess = ftpClient.login(user, pwd);
		} catch (IOException e) {
			logger.error("连接FTP服务器异常", e);
		}
		return isSuccess;
	}

	@Override
	@Transactional
	public boolean deleteFTPFile(String pathname){
		// TODO Auto-generated method stub
		boolean deleteResult = false;
		if (connectServer(Constant.ftpIp, Constant.ftpPort, Constant.ftpUser, Constant.ftpPwd)) {
			try {
				deleteResult = ftpClient.deleteFile(pathname);
				logger.info("删除文件:{}", deleteResult);
			} catch (IOException e) {
				logger.error("删除文件异常", e);
				deleteResult = false;
			} finally {
				try {
					ftpClient.disconnect();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return deleteResult;
	}
	
	@Override
	@Transactional
	public Video Get(long video_id) {
		// TODO Auto-generated method stub
		try{
			return videoDAO.get(video_id);
		}catch(Exception e){
			return null;
		}
	}

	@Override
	@Transactional
	public Video Edit(Video video) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		try{
			videoDAO.update(video);
			return videoDAO.get(video.getVideo_id());
		}catch(Exception e){
			return video;
		}
	}
}
