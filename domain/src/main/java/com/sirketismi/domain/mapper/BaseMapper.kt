package com.sirketismi.entities.mapper

interface BaseMapper<Input, Output> {
    fun map(input: Input) : Output
}