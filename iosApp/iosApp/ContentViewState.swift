//
//  ContentViewState.swift
//  iosApp
//
//  Created by Àlex G. Luque on 8/1/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

private let provider = ServiceProvider()
private let emptyState = ViewState(
    isButtonEnabled: false,
    red: "",
    blue: Array(repeating: "", count: 6),
    wordAmount: 0,
    words: "",
    warningMessage: ""
)
private let localSource = WordsLocalSourceImp()

class ContentViewState: ObservableObject {
    
    @Published var viewModel = ParaulogicViewModel(
        context: provider.getCoroutineMainContext(),
        localSource: localSource,
        insertWordsUseCase: InsertWordsUseCaseImp(localSource: localSource),
        getWordsUseCase: GetWordsUseCaseImp(localSource: localSource)
    )
    @Published var state = emptyState
    @Published var isButtonEnabled = emptyState.isButtonEnabled
    @Published var red = emptyState.red
    @Published var blue = emptyState.blue
    @Published var blueUpperLeft = ""
    @Published var blueUpperRight = ""
    @Published var blueCenterLeft = ""
    @Published var blueCenterRight = ""
    @Published var blueLowerLeft = ""
    @Published var blueLowerRight = ""
    @Published var words = emptyState.words
    @Published var wordAmount = emptyState.wordAmount
    @Published var warningMessage = emptyState.warningMessage
    
    init() {
        viewModel.observeState { [self] newState in
            state = newState
            isButtonEnabled = newState.isButtonEnabled
            red = newState.red
            blue = newState.blue
            words = newState.words
            wordAmount = newState.wordAmount
            warningMessage = newState.warningMessage
            
            if !newState.blue[0].isEmpty {
                blueUpperLeft = newState.blue[0]
            }
            
            if !newState.blue[1].isEmpty {
                blueUpperRight = newState.blue[1]
            }
            
            if !newState.blue[2].isEmpty {
                blueCenterLeft = newState.blue[2]
            }
            
            if !newState.blue[3].isEmpty {
                blueCenterRight = newState.blue[3]
            }
            
            if !newState.blue[4].isEmpty {
                blueLowerLeft = newState.blue[4]
            }
            
            if !newState.blue[5].isEmpty {
                blueLowerRight = newState.blue[5]
            }
        }
    }
    
    func setBlue() -> [String] {
        return [
            blueUpperLeft,
            blueUpperRight,
            blueCenterLeft,
            blueCenterRight,
            blueLowerLeft,
            blueLowerRight
        ]
    }
}
