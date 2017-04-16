package com.hp.webedu.test;

import java.io.File;
import java.util.Date;
import java.util.UUID;

import it.sauronsoftware.jave.AudioAttributes;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.EncodingAttributes;
import it.sauronsoftware.jave.InputFormatException;
import it.sauronsoftware.jave.MultimediaInfo;
import it.sauronsoftware.jave.VideoAttributes;
import it.sauronsoftware.jave.VideoSize;

public class Main {
	public static void main(String[] args){
		String string = UUID.randomUUID().toString();
		System.out.println(string.length());
//		File source = new File("source.avi");  
//		File target = new File("target.flv");  
//		AudioAttributes audio = new AudioAttributes();  
//		audio.setCodec("libmp3lame");  
//		audio.setBitRate(new Integer(64000));  
//		audio.setChannels(new Integer(1));  
//		audio.setSamplingRate(new Integer(22050));  
//		VideoAttributes video = new VideoAttributes();  
//		video.setCodec("flv");  
//		video.setBitRate(new Integer(160000));  
//		video.setFrameRate(new Integer(15));  
//		video.setSize(new VideoSize(400, 300));  
//		EncodingAttributes attrs = new EncodingAttributes();  
//		attrs.setFormat("flv");  
//		attrs.setAudioAttributes(audio);  
//		attrs.setVideoAttributes(video);  
//		Encoder encoder = new Encoder();  
//		encoder.encode(source, target, attrs);
		
//		Long time = new Date().getTime();
//		Long currentTimeMillis = System.currentTimeMillis();
//		System.out.println(time);
//		System.out.println(currentTimeMillis);
		
	}

}
