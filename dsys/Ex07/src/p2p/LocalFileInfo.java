package p2p;

import java.io.Serializable;

public class LocalFileInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	protected String filePath;
	protected String fileName;
	protected long fileSize;
	protected int fileId;

	public LocalFileInfo(int id, String name, String path, long size) {
		this.filePath = path;
		this.fileName = name;
		this.fileSize = size;
		this.fileId = id;
	}

	public long getLength() {
		return fileSize;
	}

	public String getFilename() {
		return fileName;
	}

	public String getAbsFilepath() {
		return filePath;
	}

	@Override
	public String toString() {
		return fileName;
	}

}
