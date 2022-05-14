package com.yoloroy.gameoflife.util

fun <T> Iterable<T>.infiniteIterator() = iterator {
    while (true) yieldAll(this@infiniteIterator)
}
