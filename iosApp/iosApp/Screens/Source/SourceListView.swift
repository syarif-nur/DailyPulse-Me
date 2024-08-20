//
//  SourceListView.swift
//  iosApp
//
//  Created by Syarif on 10/08/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared


struct SourceListView: View {
    @EnvironmentObject var viewModel: SourcesScreen.SourcesViewModelWrapper
    
    var body: some View {
        VStack{
            
            if viewModel.sourcesState.loading{
                Loader()
            }
            
            if let error = viewModel.sourcesState.error {
                ErrorMessage(message: error)
            }
            
            if(!viewModel.sourcesState.sources.isEmpty){
                ScrollView{
                    LazyVStack(spacing:10){
                        ForEach(viewModel.sourcesState.sources, id:\.self){
                            source in SourceItemView(source: source)
                        }
                    }
                }
            }
        }.onAppear{
            self.viewModel.startObserving()
        }
        
    }
}

struct SourceItemView: View {
    var source: Source

    var body: some View {
        VStack(alignment: .leading, spacing: 8) {
//            AsyncImage(url: URL(string: source.imageUrl)) {phase in
//                if phase.image != nil{
//                    phase.image!
//                        .resizable()
//                        .aspectRatio(contentMode: .fit)
//                }else if phase.error != nil{
//                    Text("Image Error")
//                }else{
//                    ProgressView()
//                }
//            }
            Text(source.name).font(.title).fontWeight(.bold)
            Text(source.desc)
            Text(source.langCountry).frame(maxWidth: .infinity, alignment: .trailing).foregroundStyle(.gray)
        }.padding(16)
    }
}
