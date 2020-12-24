// ======================================================================
//
//      Copyright (C) 北京国双科技有限公司
//                    http://www.gridsum.com
//
//      保密性声明：此文件属北京国双科技有限公司所有，仅限拥有由国双科技
//      授予了相应权限的人所查看和所修改。如果你没有被国双科技授予相应的
//      权限而得到此文件，请删除此文件。未得国双科技同意，不得查看、修改、
//      散播此文件。
//
//
// ======================================================================

package com.kai.api.common.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@Setter
@ResponseStatus(value= HttpStatus.NOT_FOUND)
public class NotFoundException extends BaseException {
    private static final int code = 400;

    private static final String DEFAULT_MESSAGE = "资源未找到";

    public NotFoundException(){
        this(DEFAULT_MESSAGE, null);
    }
    public NotFoundException(String message) {
        this(message, null);
    }

    public NotFoundException(Throwable t) {
        this(DEFAULT_MESSAGE, t);
    }

    public NotFoundException(String message, Throwable t) {
        super(message, t);
    }

    public int getCode() {
        return code;
    }
}
