import SwiftUI
import shared

struct ContentView: View {
    
    @State private var shouldOpenAbout = false
    @State private var shouldOpenSource = false
    
    var body: some View {
        
        let articlesScreen = ArticlesScreen(viewModel: .init())

        NavigationStack{
            articlesScreen
                .toolbar{
                    ToolbarItem{
                        Button{
                            shouldOpenSource = true
                        } label: {
                            Label("Source", systemImage: "list.bullet.rectangle").labelStyle(.titleAndIcon )
                        }
                        .popover(isPresented: $shouldOpenSource, content: {
                            SourcesScreen()
                        })
                    }
                    ToolbarItem{
                        Button{
                            shouldOpenAbout = true
                        } label: {
                            Label("About", systemImage: "info.circle").labelStyle(.titleAndIcon )
                        }
                        .popover(isPresented: $shouldOpenAbout, content: {
                            AboutScreen()
                        })
                    }
                }
        }.refreshable {
            articlesScreen.viewModel.articlesViewModel.getArticles(forceFetch: true)
        }
    }
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
