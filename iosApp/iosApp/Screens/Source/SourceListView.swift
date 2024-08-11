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
    @EnvironmentObject var viewModel: SourcesScreen.ArticlesViewModelWrapper
    
    var body: some View {
        VStack{
            
            if viewModel.articlesState.loading{
                Loader()
            }
            
            if let error = viewModel.articlesState.error {
                ErrorMessage(message: error)
            }
            
            if(!viewModel.articlesState.articles.isEmpty){
                ScrollView{
                    LazyVStack(spacing:10){
                        ForEach(viewModel.articlesState.articles, id:\.self){
                            article in SourceItemView(article: article)
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
    var article: Article

    var body: some View {
        VStack(alignment: .leading, spacing: 8) {
            AsyncImage(url: URL(string: article.imageUrl)) {phase in
                if phase.image != nil{
                    phase.image!
                        .resizable()
                        .aspectRatio(contentMode: .fit)
                }else if phase.error != nil{
                    Text("Image Error")
                }else{
                    ProgressView()
                }
            }
            Text(article.title).font(.title).fontWeight(.bold)
            Text(article.desc)
            Text(article.date).frame(maxWidth: .infinity, alignment: .trailing).foregroundStyle(.gray)
        }.padding(16)
    }
}
