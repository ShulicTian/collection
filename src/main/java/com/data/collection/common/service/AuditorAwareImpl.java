package com.data.collection.common.service;

import com.data.collection.sys.utils.UserUtils;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * SpringDataJpa 审计（即初始化createBy及updateBy）
 *
 * @author ShulicTian
 */
@Component
public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of(UserUtils.getCurrentUserId()!=null?UserUtils.getCurrentUserId().toString():"");
    }

}
