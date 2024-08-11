//
//  SourcesScreen.swift
//  iosApp
//
//  Created by Syarif on 10/08/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

extension SourcesScreen{
    
    @MainActor
    class ArticlesViewModelWrapper: ObservableObject {
        let articlesViewModel: ArticlesViewModel
        
        
        init() {
            articlesViewModel = ArticlesInjector().articlesViewModel
            articlesState = articlesViewModel.articlesState.value
        }
        
        @Published var articlesState: ArticlesState
        
        func startObserving() {
            Task {
                for await articlesS in articlesViewModel.articlesState {
                    self.articlesState = articlesS
                }
            }
        }
    }
}


struct SourcesScreen: View {
    @Environment(\.dismiss)
    private var dismiss
    @StateObject private var viewModelWrapper = ArticlesViewModelWrapper()
    
    var body: some View{
        NavigationStack{
            SourceListView().navigationTitle("Source")
                .toolbar{
                    ToolbarItem(placement: .principal, content: {
                        Text("Source").bold()
                    })
                    ToolbarItem(placement: .primaryAction){
                        Button{
                            dismiss()
                        }label: {
                            Text("Done").bold()
                        }
                    }
                }
                .environmentObject(viewModelWrapper)
        }.onAppear{
            viewModelWrapper.startObserving()
        }
    }
    
}

#Preview {
    SourcesScreen()
}
