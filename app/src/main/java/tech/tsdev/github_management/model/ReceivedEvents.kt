package tech.tsdev.github_management.model

import com.google.gson.annotations.SerializedName

data class ReceivedEvents(
    val actor: Actor,
    @SerializedName("created_at") val createdAt: String,
    val id: String,
    val org: Org,
    val payload: Payload,
    val `public`: Boolean,
    val repo: Repo,
    val type: String
) {
    fun whichStarRepoORCreateRepo(type: String): String =
        when (type) {
            "WatchEvent" -> "${repo.name}d에 스타를 줌"
            else -> "레포지토리 ${repo.name}를 생성"
        }


//    fun currentTimeAtElapsedTime(date: String) {
//
//    }
}

data class Repo(
    val id: Int,
    val name: String,
    val url: String
)

data class Org(
    @SerializedName("avatar_url") val avatarUrl: String,
    val gravatar_id: String,
    val id: Int,
    val login: String,
    val url: String
)

data class Actor(
    @SerializedName("avatar_url") val avatarUrl: String,
    val display_login: String,
    val gravatar_id: String,
    val id: Int,
    val login: String,
    val url: String
)

data class Payload(
    val action: String
)