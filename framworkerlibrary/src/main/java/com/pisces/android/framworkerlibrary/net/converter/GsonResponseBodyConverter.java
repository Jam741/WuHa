/*
 * Copyright (C) 2015 Square, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.pisces.android.framworkerlibrary.net.converter;

import android.util.Log;

import com.google.gson.TypeAdapter;
import com.pisces.android.framworkerlibrary.net.ApiModel;
import com.pisces.android.framworkerlibrary.net.exception.ApiException;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

final class GsonResponseBodyConverter<T> implements Converter<ResponseBody, Object> {

    private final TypeAdapter<T> adapter;

    GsonResponseBodyConverter(TypeAdapter<T> adapter) {
        this.adapter = adapter;
    }

    @Override
    public Object convert(ResponseBody value) throws IOException {
        try {
            ApiModel apiModel = (ApiModel) adapter.fromJson(value.charStream());
            if (apiModel.getErrorCode() != 0) {
                throw new ApiException(apiModel.getErrorCode(), apiModel.getMessage(), apiModel.getDebugMessage());
            } else if (apiModel.getErrorCode() == 0) {
                return apiModel.getDatas();
            }
        } finally {
            value.close();
        }
        return null;
    }
}
