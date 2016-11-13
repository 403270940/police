package com.police.dao;

import com.police.model.Reply;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by liyy on 16/10/27.
 */
public interface ReplyRepository extends PagingAndSortingRepository<Reply,Integer> {
    List<Reply> findByThemeid(int themeid);
    int countByThemeid(int themeid);
}
