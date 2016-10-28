package com.police.dao;

import com.police.model.Captcha;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by liyy on 16/10/27.
 */
public interface CaptchaRepository extends PagingAndSortingRepository<Captcha,Integer> {
}
