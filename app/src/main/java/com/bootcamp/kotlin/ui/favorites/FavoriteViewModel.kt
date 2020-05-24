import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bootcamp.kotlin.ui.common.Scope
import com.bootcamp.kotlin.ui.favorites.FavoriteUiModel
import com.movies.domain.FavoriteMovie
import com.movies.interactor.GetFavoriteMovies
import kotlinx.coroutines.launch

class FavoriteViewModel(
    private val getFavoriteMovies: GetFavoriteMovies
) : ViewModel(), Scope by Scope.Impl() {

    init {
        createScope()
    }

    private val _model = MutableLiveData<FavoriteUiModel>()
    val model: LiveData<FavoriteUiModel>
        get() {
            if (_model.value == null) {
                getFavoriteMovies()
            }

            return _model
        }

    private fun getFavoriteMovies() {
        launch {
            _model.value = FavoriteUiModel.Loading
            val response = getFavoriteMovies.invoke()
            _model.value = FavoriteUiModel.Content(response.data!!)
        }
    }

    fun onMovieClicked(favoriteMovie: FavoriteMovie) {
        _model.value = FavoriteUiModel.Navigation(favoriteMovie)
    }

    override fun onCleared() {
        destroyScope()
        super.onCleared()
    }
}