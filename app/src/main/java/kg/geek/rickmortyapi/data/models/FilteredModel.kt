package kg.geek.rickmortyapi.data.models

class FilteredModel(var character: Character, var episode: Episode, var location: Location) {

    @JvmName("getCharacter1")
    fun getCharacter(): Character {
        return character
    }

    @JvmName("setCharacter1")
    fun setCharacter(character: Character) {
        this.character = character
    }

    @JvmName("getCharacter1")
    fun getEpisode(): Episode {
        return episode
    }

    @JvmName("setCharacter1")
    fun setEpisode(episode: Episode) {
        this.episode = episode
    }

    @JvmName("getCharacter1")
    fun getLocation(): Location {
        return location
    }

    @JvmName("setCharacter1")
    fun setLocation(location: Location) {
        this.location = location
    }

}