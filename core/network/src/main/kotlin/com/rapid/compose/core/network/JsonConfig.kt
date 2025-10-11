package com.rapid.compose.core.network

import kotlinx.serialization.json.Json

object JsonConfig {
    val default = Json {
        ignoreUnknownKeys = true  // 忽略未知字段
        encodeDefaults = true      // 编码默认值
        coerceInputValues = true   // 强制输入值类型转换
        isLenient = true          // 宽松模式
    }
}