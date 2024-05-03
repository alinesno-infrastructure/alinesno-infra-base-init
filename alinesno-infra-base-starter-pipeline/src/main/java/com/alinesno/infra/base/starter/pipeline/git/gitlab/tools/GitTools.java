package com.alinesno.infra.base.starter.pipeline.git.gitlab.tools;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.jgit.api.AddCommand;
import org.eclipse.jgit.api.CheckoutCommand;
import org.eclipse.jgit.api.CloneCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.PullCommand;
import org.eclipse.jgit.api.PushCommand;
import org.eclipse.jgit.api.Status;
import org.eclipse.jgit.api.errors.CannotDeleteCurrentBranchException;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.NoFilepatternException;
import org.eclipse.jgit.api.errors.NotMergedException;
import org.eclipse.jgit.internal.storage.file.FileRepository;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by liuyang on 2017/12/27.
 */
public class GitTools {

	private final static Logger log = LoggerFactory.getLogger(GitTools.class);

	/**
	 * 创建仓库
	 * <p>
	 * 如果仓库不存在就创建仓库 仓库存在，返回存在的仓库
	 *
	 * @param repoDir 仓库文件夹
	 * @return 仓库实例
	 */
	public static Repository createGitRepository(File repoDir) {
		Repository ret = null;
		File repoGitDir = getGitAbsolutePath(repoDir);
		try {
			if (!repoGitDir.exists()) {
				// 创建本地仓的库
				ret = FileRepositoryBuilder.create(repoGitDir);
				ret.create();
			} else {
				ret = openGitRepository(repoDir);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ret;
	}

	/**
	 * 创建仓库
	 *
	 * @param dirPath 仓库文件夹路径
	 * @return 仓库实例
	 */
	public static Repository createGitRepository(String dirPath) {
		return createGitRepository(new File(dirPath));
	}

	/**
	 * 删除仓库
	 *
	 * @param repoDir 仓库文件夹
	 * @return
	 */
	public static boolean deleteGitRepository(File repoDir) {
		boolean ret = false;
		if (repoDir.exists()) {
			deleteFolder(repoDir);
			ret = repoDir.delete();
		}
		return ret;
	}

	/**
	 * 删除仓库
	 *
	 * @param repoDir
	 * @return
	 */
	public static boolean deleteGitRepository(String repoDir) {
		File repoGitDir = getGitAbsolutePath(new File(repoDir));
		Repository repository = null;
		try {
			repository = new FileRepository(repoGitDir.getAbsoluteFile());
			try (Git git = new Git(repository)) {
				git.branchDelete().setBranchNames("master").setForce(true).call();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (CannotDeleteCurrentBranchException e) {
			e.printStackTrace();
		} catch (NotMergedException e) {
			e.printStackTrace();
		} catch (GitAPIException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 打开仓库
	 *
	 * @param repoDir 仓库文件夹
	 * @return
	 */
	public static Repository openGitRepository(File repoDir) {
		try {
			File file = getGitAbsolutePath(repoDir);
			if (file.exists()) {
				return new FileRepository(file);
			}
		} catch (IOException e) {
			log.error(e.getMessage());
		}
		return null;
	}

	/**
	 * 打开仓库
	 *
	 * @param dirPath
	 * @return
	 */
	public static Repository openGitRepository(String dirPath) {
		return openGitRepository(new File(dirPath));
	}

	/**
	 * 克隆远程仓库（私有，需要用户名、密码） 本地不能有相同名的仓库
	 *
	 * @param httpsUrl https地址
	 * @param repoDir  仓库文件夹
	 * @param username git用户名
	 * @param password git密码
	 * @return
	 */
	public static boolean cloneGit(String httpsUrl, File repoDir, String username, String password) {
		boolean ret = false;
		Git git = null;
		try {
			CloneCommand cloneCommand = Git.cloneRepository().setURI(httpsUrl);
			if (username != null && password != null) {
				cloneCommand.setCredentialsProvider(new UsernamePasswordCredentialsProvider(username, password));
			}
			if (repoDir != null) {
				// 目录为null则克隆到当前项目下的目录，不建议这样做
				cloneCommand.setDirectory(repoDir);
			}
			git = cloneCommand.call();
			ret = true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			if (git != null) {
				git.close();
			}
		}
		return ret;
	}

	public static boolean cloneGit(String httpsUrl, String dirPath, String username, String password) {
		return cloneGit(httpsUrl, new File(dirPath), username, password);
	}

	/**
	 * 克隆远程仓库（公共，不需要用户名、密码） 本地不能有相同名的仓库
	 *
	 * @param httpsUrl https地址
	 * @param repoDir  仓库文件夹
	 * @return
	 */
	public static boolean cloneGit(String httpsUrl, File repoDir) {
		return cloneGit(httpsUrl, repoDir, null, null);
	}

	public static boolean cloneGit(String httpsUrl, String dirPath) {
		return cloneGit(httpsUrl, new File(dirPath), null, null);
	}

	public static boolean checkoutGit(File repoDir, String branchName) {
		return checkoutGit(repoDir, branchName, null, null);
	}

	public static boolean checkoutGit(String repoDir, String brachName, String userName, String password) {
		return checkoutGit(new File(repoDir), brachName, userName, password);
	}

	/**
	 * 私用库检出
	 * 
	 * @param repoDir
	 * @param username
	 * @param password
	 * @return
	 */
	public static boolean checkoutGit(String repoDir, String username, String password) {
		return checkoutGit(repoDir, null, username, password);
	}

	/**
	 * 检出仓库 本地必须有仓库
	 *
	 * @param repoDir    仓库文件夹
	 * @param branchName 分支全名
	 * @return
	 */
	public static boolean checkoutGit(File repoDir, String branchName, String userName, String password) {
		Repository repository = null;
		try {
			if (branchName == null) {
				branchName = "master";
			}
			File repoGitDir = getGitAbsolutePath(repoDir);
			if (!repoGitDir.exists()) {
				log.error("仓库不存在");
				return false;
			} else {
				repository = openGitRepository(repoDir);
				PullCommand pullCommand;
				try (Git git = new Git(repository)) {
					CheckoutCommand checkoutCommand = git.checkout();
					checkoutCommand.setName(branchName).call();
					pullCommand = git.pull();
				}
				if (!StringUtils.isEmpty(userName) && !StringUtils.isEmpty(password)) {
					pullCommand.setCredentialsProvider(new UsernamePasswordCredentialsProvider(userName, password));
				}
				pullCommand.call();
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			if (repository != null) {
				repository.close();
			}
		}
	}

	public static boolean checkoutGit(String repoDir, String branchName) {
		return checkoutGit(new File(repoDir), branchName);
	}

	public static boolean checkoutGit(File repoDir) {
		return checkoutGit(repoDir, null);
	}

	/**
	 * 公有库检出
	 * 
	 * @param repoDir
	 * @return
	 */
	public static boolean checkoutGit(String repoDir) {
		return checkoutGit(repoDir, null);
	}

	/**
	 * 获取仓库状态
	 *
	 * @param repoDir 仓库文件夹
	 * @return
	 */
	public static Status getGitStatus(File repoDir) {
		Status ret = null;
		Repository repository = null;
		try {
			File repoGitDir = getGitAbsolutePath(repoDir);
			if (!repoGitDir.exists()) {
				log.error("仓库不存在");
				return null;
			} else {
				repository = new FileRepository(repoGitDir.getAbsoluteFile());
				try (Git git = new Git(repository)) {
					ret = git.status().call();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (repository != null) {
				repository.close();
			}
		}
		return ret;
	}

	public static Status getGitStatus(String dirPath) {
		return getGitStatus(new File(dirPath));
	}

	/**
	 * pull
	 *
	 * @param repoDir
	 * @return
	 */
	public static boolean pullGit(File repoDir) {
		boolean ret = false;
		File repoGitDir = getGitAbsolutePath(repoDir);
		if (!repoGitDir.exists()) {
			log.error("仓库不存在");
			return false;
		} else {
			Repository repository = null;
			try {
				repository = openGitRepository(repoDir);
				PullCommand pullCommand;
				try (Git git = new Git(repository)) {
					pullCommand = git.pull();
				}
				pullCommand.call();
				ret = true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			} finally {
				if (repository != null) {
					repository.close();
				}
			}
		}
		return ret;
	}

	/**
	 * 创建新分支
	 *
	 * @param branchName
	 * @param repoDir
	 * @return
	 */
	public static boolean createBranch(String branchName, File repoDir) {
		boolean ret = false;
		String newBranchIndex = "refs/heads/" + branchName;
		File repoGitDir = getGitAbsolutePath(repoDir);
		if (!repoGitDir.exists()) {
			log.error("仓库不存在");
			return false;
		} else {
			Repository repository = null;
			Git git = null;
			try {
				repository = new FileRepository(repoGitDir.getAbsoluteFile());
				git = new Git(repository);
				// 检查分支是否存在
				List<Ref> refList = git.branchList().call();
				for (Ref ref : refList) {
					if (ref.getName().equals(newBranchIndex)) {
						git.branchDelete().setBranchNames(branchName).setForce(true).call();
						break;
					}
				}
				// 新建分支
				Ref ref = git.branchCreate().setName(branchName).call();
				// 推送到远程
				git.push().add(ref).call();
				ret = true;
			} catch (Exception e) {
				log.error(e.getMessage());
				return false;
			} finally {
				if (repository != null) {
					repository.close();
				}
				if (git != null) {
					git.close();
				}
			}
		}
		return ret;
	}

	public static boolean createBranch(String branchName, String dirPath) {
		return createBranch(branchName, new File(dirPath));
	}

	/**
	 * 提交并推送远程仓库
	 *
	 * @param repoDir  本地仓库文件夹
	 * @param message  提交信息
	 * @param username 用户名
	 * @param password 密码
	 * @return
	 * @throws IOException 
	 * @throws GitAPIException 
	 * @throws NoFilepatternException 
	 */
	public static boolean commitAndPush(File repoDir, String username, String password, String message) throws IOException, NoFilepatternException, GitAPIException {
		boolean ret = false;
		File repoGitDir = getGitAbsolutePath(repoDir);
		if (!repoGitDir.exists()) {
			log.info("仓库不存在");
			return false;
		} else {
			Repository repository = null;
			Git git = null;
			try {
				repository = new FileRepository(repoGitDir.getAbsoluteFile());
				git = new Git(repository);
				
				AddCommand add = git.add() ; 
				add.addFilepattern(".").call() ; 
				
				Status repoStatus = getGitStatus(repoDir);
				Set<String> untractedFiles = repoStatus.getUntracked();
				for (String fileName : untractedFiles) {
					git.add().addFilepattern(fileName).call();
				}
				git.commit().setMessage(message).call();
				PushCommand pushCommand = git.push();
				pushCommand.setCredentialsProvider(new UsernamePasswordCredentialsProvider(username, password));
				pushCommand.call();
				ret = true;
			} finally {
				if (repository != null) {
					repository.close();
				}
				if (git != null) {
					git.close();
				}
			}
		}
		return ret;
	}

	public static boolean commitAndPush(String repoDir, String username, String password, String message) throws NoFilepatternException, IOException, GitAPIException {
		return commitAndPush(new File(repoDir), username, password, message);
	}

	/**
	 * 获取git绝对路径
	 *
	 * @param repoDir
	 * @return
	 */
	private static File getGitAbsolutePath(File repoDir) {
		return new File(repoDir.getAbsoluteFile() + "/.git");
	}

	/**
	 * 删除文件夹
	 *
	 * @param file
	 */
	private static void deleteFolder(File file) {
		if (file.isFile() || file.list().length == 0) {
			file.delete();
		} else {
			File[] files = file.listFiles();
			for (int i = 0; i < files.length; i++) {
				deleteFolder(files[i]);
				files[i].delete();
			}
		}
	}

}
