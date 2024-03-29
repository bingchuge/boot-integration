/*
 * Copyright (c) 2020 pig4cloud Authors. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.example.sa.rbac.demo.common.obj;


import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 响应信息主体
 *
 * @param <T>
 * @author lengleng
 */
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Result<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	private int code;

	@Getter
	@Setter
	private String msg;

	@Getter
	@Setter
	private T data;

	public static <T> Result<T> ok() {
		return restResult(null, 200, "");
	}

	public static <T> Result<T> ok(T data) {
		return restResult(data, 200, "");
	}

	public static <T> Result<T> ok(T data, String msg) {
		return restResult(data, 200, msg);
	}

	public static <T> Result<T> failed() {
		return restResult(null, 500, "");
	}

	public static <T> Result<T> failed(String msg) {
		return restResult(null, 500, msg);
	}

	public static <T> Result<T> failed(T data) {
		return restResult(data, 500, "");
	}

	public static <T> Result<T> failed(T data, String msg) {
		return restResult(data, 500, msg);
	}

	private static <T> Result<T> restResult(T data, int code, String msg) {
		Result<T> apiResult = new Result<>();
		apiResult.setCode(code);
		apiResult.setData(data);
		apiResult.setMsg(msg);
		return apiResult;
	}

}
