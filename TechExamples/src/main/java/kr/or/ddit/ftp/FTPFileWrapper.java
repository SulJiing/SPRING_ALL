package kr.or.ddit.ftp;

import org.apache.commons.net.ftp.FTPFile;

import kr.or.ddit.fancytree.AbstractFancytreeNode;

public class FTPFileWrapper extends AbstractFancytreeNode<FTPFile> {

	public FTPFileWrapper(FTPFile data) {
		super(data);
	}

	@Override
	public String getTitle() {
		return getSource().getName();
	}

	@Override
	public String getKey() {
		return getSource().getName();
	}

	@Override
	public boolean isFolder() {
		return getSource().isDirectory();
	}

	@Override
	public boolean isExpanded() {
		return false;
	}

}
