//
//  Helper.swift
//  iosApp
//
//  Created by Syarif on 10/08/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct Loader: View{
    var body: some View{
        ProgressView()
    }
}

struct ErrorMessage: View {
    var message: String
    
    var body: some View {
        Text(message).font(.title)
    }
}

