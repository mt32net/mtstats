package net.mt32.mtstats

import net.mt32.mtstats.githubapi.model.GhUser

fun String.rep(a: String, b: Any) = replace("§$a", b.toString())

fun String.replaceWithUserInfo(user: GhUser) : String {
    return rep("stars", user.getStars())
        .rep("login", user.login)
        .rep("name", user.name ?: user.login)
        .rep("repoCount", user.repositories.totalCount)
        .rep("status", user.status?.message ?: "-")
        .rep("followers", user.following.totalCount)
        .rep("commits", user.contributionsCollection.totalCommitContributions)
        .rep("prs", user.contributionsCollection.totalPullRequestContributions)
        .rep("issues", user.contributionsCollection.totalIssueContributions)
}