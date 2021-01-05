package net.mt32.mtstats.githubapi.model

data class GHUser(
    val starredRepositories: Repositories,
    val repositories: Repositories,
    val name: String? = null,
) {
    class Repositories(totalCount: Int)
}