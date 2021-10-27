package kg.geek.rickmortyapi.data.models

data class MainResult<T>(
    var info: Info?,
    var results: ArrayList<T>?
)