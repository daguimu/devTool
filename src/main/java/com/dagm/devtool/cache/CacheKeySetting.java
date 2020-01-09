package com.dagm.devtool.cache;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CacheKeySetting {

    private StoreKey key;
    private Integer expire;
}