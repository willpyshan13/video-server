package com.e_lliam.app.video.server.utils.extjs;

import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class MeetingObjectMapper extends ObjectMapper {
	public MeetingObjectMapper() {
		this.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}
}
