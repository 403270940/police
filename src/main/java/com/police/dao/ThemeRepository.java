package com.police.dao;

import com.police.model.Theme;
import com.police.model.UploadModel;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by liyy on 16/10/27.
 */
public interface ThemeRepository extends PagingAndSortingRepository<Theme,Integer> {
    Theme findById(int id);
    List<Theme> findAll();
}
