//
//  ViewExtensions.swift
//  iosApp
//
//  Created by Àlex G. Luque on 10/1/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI

extension View {
    
    func hideKeyboard() {        
        UIApplication.shared.sendAction(
            #selector(UIResponder.resignFirstResponder),
            to: nil,
            from: nil,
            for: nil
        )
    }
}

