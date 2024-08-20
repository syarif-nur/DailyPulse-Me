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
    class SourcesViewModelWrapper: ObservableObject {
        let sourcesViewModel: SourcesViewModel
        
        
        init() {
            sourcesViewModel = SourcesInjector().sourcesViewModel
            sourcesState = sourcesViewModel.sourceState.value
        }
        
        @Published var sourcesState: SourcesState
        
        func startObserving() {
            Task {
                for await sourcesS in sourcesViewModel.sourceState {
                    self.sourcesState = sourcesS
                }
            }
        }
    }
}


struct SourcesScreen: View {
    @Environment(\.dismiss)
    private var dismiss
    @StateObject private var viewModelWrapper = SourcesViewModelWrapper()
    
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
