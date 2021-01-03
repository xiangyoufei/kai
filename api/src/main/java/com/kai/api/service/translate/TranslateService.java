package com.kai.api.service.translate;

import com.kai.api.common.Language;

public interface TranslateService {


    String translate(String text, Language from, Language to);
}
