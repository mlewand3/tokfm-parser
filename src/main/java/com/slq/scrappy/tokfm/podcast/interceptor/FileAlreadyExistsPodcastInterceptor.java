package com.slq.scrappy.tokfm.podcast.interceptor;

import com.slq.scrappy.tokfm.podcast.Podcast;
import com.slq.scrappy.tokfm.podcast.repository.PodcastRepository;

import static org.apache.commons.lang3.StringUtils.substring;

public class FileAlreadyExistsPodcastInterceptor implements PodcastInterceptor {

	public static final int CONSOLE_LINE_WIDTH = 150;
	private PodcastRepository podcastRepository;

	public FileAlreadyExistsPodcastInterceptor(PodcastRepository podcastRepository) {
		this.podcastRepository = podcastRepository;
	}

	@Override
	public boolean process(Podcast podcast) {
		String filename = podcast.getTargetFilename();

		if (podcastRepository.exists(filename)) {
			String shortFilename = substring(filename, 0, CONSOLE_LINE_WIDTH);
			System.out.println(String.format("%-150s : Already exists. Exiting...", shortFilename));
			return false;
		}

		return true;
	}
}