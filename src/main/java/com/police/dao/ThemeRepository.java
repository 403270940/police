//package com.police.dao;
//
//import com.police.model.ResponseModel.ThemeSummary;
//import com.police.model.Theme;
//import com.police.model.UploadModel;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.PagingAndSortingRepository;
//
//import java.util.List;
//
///**
// * Created by liyy on 16/10/27.
// */
//public interface ThemeRepository extends PagingAndSortingRepository<Theme,Integer> {
//    Theme findById(int id);
//    List<Theme> findAll();
//    @Query("select a.id as id,a.phone as creator,b.title as title,b.create_time as createTime,count(c.id) as replyCount from user a,theme b,reply c  where a.id=b.uid and b.id=c.themeid and a.id = 1")
//    Object query(int id);
//
//}
