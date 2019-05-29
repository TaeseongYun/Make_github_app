package tech.tsdev.github_management.model

import com.google.gson.annotations.SerializedName

data class GetRepoCommitList(
    val author: Author,
    val comments_url: String,
    val commit: Commit,
    val committer: Committer,
    val html_url: String,
    val node_id: String,
    val parents: List<Any>,
    val sha: String,
    val url: String
)

data class Commit(
    val author: Author,
    @SerializedName("comment_count")  val commentCount: Int,
    val committer: Committer,
    val message: String,
    val tree: Tree,
    val url: String,
    val verification: Verification
)

data class Verification(
    val payload: Any,
    val reason: String,
    val signature: Any,
    val verified: Boolean
)

data class Committer(
    val date: String,
    val email: String,
    val name: String,
    @SerializedName("avatar_url")  val avatarUrl: String,
    val events_url: String,
    val followers_url: String,
    val following_url: String,
    val gists_url: String,
    val gravatar_id: String,
    val html_url: String,
    val id: Int,
    val login: String,
    val node_id: String,
    val organizations_url: String,
    val received_events_url: String,
    val repos_url: String,
    val site_admin: Boolean,
    val starred_url: String,
    val subscriptions_url: String,
    val type: String,
    val url: String
)

data class Author(
    val date: String,
    val email: String,
    val name: String
)

data class Tree(
    val sha: String,
    val url: String
)

data class AuthorX(
    val avatar_url: String,
    val events_url: String,
    val followers_url: String,
    val following_url: String,
    val gists_url: String,
    val gravatar_id: String,
    val html_url: String,
    val id: Int,
    val login: String,
    val node_id: String,
    val organizations_url: String,
    val received_events_url: String,
    val repos_url: String,
    val site_admin: Boolean,
    val starred_url: String,
    val subscriptions_url: String,
    val type: String,
    val url: String
)