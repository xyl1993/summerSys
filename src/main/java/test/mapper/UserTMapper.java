package test.mapper;

import java.util.List;
import java.util.Map;

import test.model.UserTModel;

public interface UserTMapper {
    public List<UserTModel> getUserTList(Map<String,Object> paramsMap);
}
