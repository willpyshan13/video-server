package com.e_lliam.app.video.server.utils.extjs;

import java.io.IOException;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.ser.std.SerializerBase;

import com.e_lliam.app.video.server.pojo.extjs.ExtTreeNode;
import com.google.common.collect.Maps;

public class ExtTreeNodeSerializer extends SerializerBase<ExtTreeNode>{
	private static ObjectMapper om=new ObjectMapper();

	protected ExtTreeNodeSerializer() {
		super(ExtTreeNode.class,true);
	}

	@Override
	public void serialize(ExtTreeNode data, JsonGenerator jgen,
			SerializerProvider arg2) throws IOException,
			JsonGenerationException {
		
		Map<String, Object> rst=Maps.newHashMap(data.getOthers());
		rst.put("leaf", data.isLeaf());
		rst.put("expanded", data.isExpanded());
		if(data.getData()!=null){
			rst.put("data", data.getData());
		}
		om.writeValue(jgen, rst);
	}

}
