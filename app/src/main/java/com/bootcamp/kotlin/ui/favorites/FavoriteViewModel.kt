import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bootcamp.kotlin.ui.common.Event
import com.bootcamp.kotlin.ui.common.Scope
import com.bootcamp.kotlin.ui.common.ScopeViewModel
import com.bootcamp.kotlin.ui.favorites.FavoriteUiModel
import com.movies.domain.FavoriteMovie
import com.movies.interactor.GetFavoriteMovies
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class FavoriteViewModel(
    uiDispatcher: CoroutineDispatcher,
    private val getFavoriteMovies: GetFavoriteMovies
) : ScopeViewModel(uiDispatcher) {

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

    private val _navigation = MutableLiveData<Event<FavoriteMovie>>()
    val navigation: LiveData<Event<FavoriteMovie>> = _navigation

    private fun getFavoriteMovies() {
        launch {
            _model.value = FavoriteUiModel.Loading
            val response = getFavoriteMovies.invoke()
            _model.value = FavoriteUiModel.Content(response.data!!)
        }
    }

    fun onMovieClicked(favoriteMovie: FavoriteMovie) {
        _navigation.value = Event(favoriteMovie)
    }

    override fun onCleared() {
        destroyScope()
        super.onCleared()
    }
}