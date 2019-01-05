package com.hades.kotlintrainning.data.api.response

open class BaseItemResponse<Item>(val item: Item? = null) : BaseResponse()