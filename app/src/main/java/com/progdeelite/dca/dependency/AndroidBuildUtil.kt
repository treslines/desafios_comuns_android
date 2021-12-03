package com.progdeelite.dca.dependency

fun parseGitCommitCount(): Int {
    return Runtime.getRuntime()
        .exec("git rev-list --count HEAD")
        .inputStream
        .reader()
        .readLines()
        .lastOrNull()
        ?.also { println("git commit count: $it") }
        ?.toIntOrNull()
        ?: 5000.also { println("failed parsing git rev count. falling back to: $it") }
}

fun parseGitBranchName(): String {
    return Runtime.getRuntime()
        .exec("git rev-parse --abbrev-ref HEAD")
        .inputStream
        .reader()
        .readLines()
        .last()
        .also { println("branch name: $it") }
}