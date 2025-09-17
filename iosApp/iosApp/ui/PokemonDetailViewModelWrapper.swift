import SwiftUI
import ComposeApp

class PokemonDetailViewModelWrapper: ObservableObject {
    @Published var state: PokemonDetailUiState = PokemonDetailUiStateLoading()
    private var closeable: Closeable?

    init(viewModel: PokemonDetailViewModel, pokemonName: String) {
        // Start observing state using the iOS-friendly function
        closeable = StateFlowObserverKt.observeStateFlow(stateFlow: viewModel.uiState) { newState in
            if let typedState = newState as? PokemonDetailUiState {
                DispatchQueue.main.async {
                    self.state = typedState
                }
            }
        }
        // Trigger fetch
        viewModel.handleAction(action: PokemonDetailActionFetchData(pokemonName: pokemonName))
    }

    deinit {
        closeable?.close()
    }
}
