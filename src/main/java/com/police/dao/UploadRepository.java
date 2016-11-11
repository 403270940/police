package com.police.dao;

import com.police.model.Announcement;
import com.police.model.UploadModel;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by liyy on 16/10/27.
 */
public interface UploadRepository extends PagingAndSortingRepository<UploadModel,Integer> {
    List<UploadModel> findAll();
    List<UploadModel> findByUidAndType(String uid,String type);
    UploadModel findByUidAndIdAndType(String uid,int id,String type);
}
