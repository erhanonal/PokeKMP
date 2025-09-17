import UIKit
import SwiftUI
import ComposeApp

class IOSNativeViewFactory: NativeViewFactory {
    static var shared = IOSNativeViewFactory()

    func createPokemonDetailScreen(
        pokemonName: String,
        onBackClick: @escaping () -> Void
    ) -> UIViewController {
        let viewModel : PokemonDetailViewModel = get()
        let wrapper = PokemonDetailViewModelWrapper(viewModel: viewModel, pokemonName: pokemonName)
        let view = PokemonDetailScreen(wrapper: wrapper, onBackClick: onBackClick)
        let hosting = UIHostingController(rootView: view)
        return UINavigationController(rootViewController: hosting)
    }
}

struct PokemonDetailScreen: View {
    @ObservedObject var wrapper: PokemonDetailViewModelWrapper
    let onBackClick: () -> Void

    var body: some View {
        content
        .navigationTitle(title)
        .navigationBarTitleDisplayMode(.inline)
        .toolbar {
            ToolbarItem(placement: .navigationBarLeading) {
                Button(action: onBackClick) {
                    Image(systemName: "chevron.backward")
                        .foregroundColor(.white)
                }
            }
        }
        .toolbarBackground(Color.blue, for: .navigationBar)
        .toolbarBackground(.visible, for: .navigationBar)
    }

    @ViewBuilder
    private var content: some View {
        switch wrapper.state {
        case is PokemonDetailUiStateLoading:
            PokemonDetailLoadingView()
        case is PokemonDetailUiStateError:
            PokemonDetailErrorView()
        case let success as PokemonDetailUiStateSuccess:
            PokemonDetailSuccessView(displayName: success.model.displayName, imageUrl: success.model.imageUri)
        default:
            Text("Unknown state")
                .foregroundColor(.gray)
                .frame(maxWidth: .infinity, maxHeight: .infinity)
                .background(Color(.systemBackground))
        }
    }

    private var title: String {
        if let success = wrapper.state as? PokemonDetailUiStateSuccess {
            return success.model.displayName
        }
        return ""
    }
}


struct PokemonDetailLoadingView: View {
    var body: some View {
        VStack(spacing: 12) {
            ProgressView()
            Text("Loading Pokémon...")
                .font(.headline)
                .foregroundColor(.blue)
        }
        .frame(maxWidth: .infinity, maxHeight: .infinity)
        .background(Color(.systemBackground))
    }
}

struct PokemonDetailErrorView: View {
    var body: some View {
        VStack(spacing: 8) {
            Text("Oops!")
                .font(.title)
                .fontWeight(.bold)
                .foregroundColor(.red)
            Text("Something went wrong while loading the Pokémon.")
                .multilineTextAlignment(.center)
                .foregroundColor(.red)
        }
        .frame(maxWidth: .infinity, maxHeight: .infinity)
        .background(Color(.systemBackground))
    }
}

struct PokemonDetailSuccessView: View {
    let displayName: String
    let imageUrl: String

    var body: some View {
        VStack(spacing: 16) {

            AsyncImage(url: URL(string: imageUrl)) { image in
                image
                    .resizable()
                    .scaledToFit()
                    .frame(width: 80, height: 80)
                    .background(
                        Circle().fill(Color.primary.opacity(0.2))
                    )
            } placeholder: {
                EmptyView()
            }

            Text(displayName)
                .font(.title)
                .fontWeight(.bold)

            Text("Pokémon details will be loaded here in the future")
                .foregroundColor(.secondary)
                .multilineTextAlignment(.center)
        }
        .frame(maxWidth: .infinity, maxHeight: .infinity)
        .background(Color(.systemBackground))
    }
}
