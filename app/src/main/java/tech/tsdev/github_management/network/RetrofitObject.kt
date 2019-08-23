package tech.tsdev.github_management.network

object RetrofitObject {
    const val baseGithubUrl = "https://api.github.com"

    val githubAPI: GithubInterface by lazy {
        createRetrofit(GithubInterface::class.java, baseGithubUrl)
    }
}