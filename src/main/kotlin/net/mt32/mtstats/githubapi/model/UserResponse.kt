package net.mt32.mtstats.githubapi.model

import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    val rateLimit: RateLimit,
    val user: GhUser,
)

@Serializable
data class RateLimit(
    val cost: Int,
    val limit: Int,
    val remaining: Int,
)

@Serializable
data class GhUser(
    val login: String,
    val name: String?,
    val company: String?,
    val status: Status?,
    val repositories: Repositories,
    val repositoriesContributedTo: Countable,
    val starredRepositories: Countable,
    val following: Countable,
    val organizations: Countable,
    val contributionsCollection: ContributionsCollections,
){
    fun getStars(): Int {
        var i = 0
        repositories.nodes.forEach { i += it.stargazerCount }
        return i
    }
}

@Serializable
data class ContributionsCollections(
    val totalCommitContributions: Int,
    val totalIssueContributions: Int,
    val totalPullRequestContributions: Int,
)

@Serializable
data class Status(
    val message: String,
)

@Serializable
data class Countable(
    val totalCount: Int
)

@Serializable
data class Repositories(
    val totalCount: Int,
    val nodes: List<Repository>
)

@Serializable
data class Repository(
    val stargazerCount: Int
)