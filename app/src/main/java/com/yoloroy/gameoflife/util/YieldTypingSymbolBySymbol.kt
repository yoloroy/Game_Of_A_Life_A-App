package com.yoloroy.gameoflife.util

suspend fun SequenceScope<String>.yieldTypingSymbolBySymbol(string: String) = string
    .typingSymbolBySymbolFrames
    .forEach { yield(it) }

val String.typingSymbolBySymbolFrames: List<String> get() = (1..length).map { take(it) }
