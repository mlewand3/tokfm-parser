package com.slq.scrappy.tokfm;

import org.apache.commons.io.output.CountingOutputStream;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;

import static org.apache.commons.lang3.StringUtils.substring;

public class DownloadCountingOutputStream extends CountingOutputStream {

	private static final double KILO = 1024.0;
	private static final double MEGA = 1024.0;
	private static final int CONSOLE_LINE_WIDTH = 150;
	private final String streamName;

	public DownloadCountingOutputStream(Path path) throws FileNotFoundException {
		super(new FileOutputStream(path.toFile()));
		this.streamName = path.getFileName().toString();
	}

	@Override
	protected void afterWrite(int n) throws IOException {
		super.afterWrite(n);
		printDownloadStatus();
	}

	private String getStreamName() {
		return streamName;
	}

	private double getDownloadedMB() {
		return getByteCount() / KILO / MEGA;
	}

	private void printDownloadStatus() {
		double byteCount = getDownloadedMB();
		String streamName = getStreamName();
		System.out.print(String.format("%-150s : %.2f\r", substring(streamName, 0, CONSOLE_LINE_WIDTH), byteCount));
	}
}
