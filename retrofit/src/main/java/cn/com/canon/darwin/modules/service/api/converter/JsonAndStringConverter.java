package cn.com.canon.darwin.modules.service.api.converter;

import com.squareup.okhttp.ResponseBody;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import retrofit.Converter;

/**
 * Created by cmlBeliever on 2016/6/21.
 */
public class JsonAndStringConverter extends Converter.Factory {
    @Override
    public Converter<ResponseBody, ?> fromResponseBody(Type type, Annotation[] annotations) {
        if (type == String.class) {
            return new Converter<ResponseBody, String>() {
                @Override
                public String convert(ResponseBody value) throws IOException {
                    return new String(value.bytes());
                }
            };
        } else if (type == JSONObject.class) {
            return new Converter<ResponseBody, JSONObject>() {
                @Override
                public JSONObject convert(ResponseBody value) throws IOException {
                    try {
                        return new JSONObject(new String(value.bytes()));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    return null;
                }
            };
        }
        return super.fromResponseBody(type, annotations);
    }
}
