import SwiftUI
import shared

private func getTextField(
    _ text: Binding<String>,
    _ state: ContentViewState,
    multilineTextAlignment: TextAlignment = .center,
    color: Color = .hexagonBlue,
    padding: Edge.Set
) -> some View {
    TextField("", text: text)
        .multilineTextAlignment(multilineTextAlignment)
        .frame(width: 10)
        .padding()
        .foregroundColor(color == .hexagonBlue ? .black : .white)
        .background(
            Hexagon()
                .fill(color)
                .frame(width: 65, height: 65)
        )
        .padding(padding, 5)
        .padding(.leading, color == .hexagonRed ? 5 : 0)
        .onChange(of: text.wrappedValue) { value in
            state.viewModel.onStateChange(
                red: state.red,
                blue: state.setBlue()
            )
        }
}

struct ContentView: View {
    @StateObject private var state = ContentViewState()

	var body: some View {
        ScrollView {
            VStack {
                Group {
                    UpperLetters()
                        .environmentObject(state)
                    CentralLetters()
                        .environmentObject(state)
                    LowerLetters()
                        .environmentObject(state)
                }
                .font(Font.body.bold())
                Warning()
                    .environmentObject(state)
                ResultsButton()
                    .environmentObject(state)
                Words()
                    .environmentObject(state)
            }
            .padding(.top)
            .padding(.bottom)
        }
	}
}

struct UpperLetters: View {
    @EnvironmentObject var state: ContentViewState
    
    var body: some View {
        HStack {
            Spacer()
            getTextField(
                $state.blueUpperLeft,
                state,
                multilineTextAlignment: .trailing,
                padding: .trailing
            )
            getTextField(
                $state.blueUpperRight,
                state,
                padding: .leading
            )
            Spacer()
        }
    }
}

struct CentralLetters: View {
    @EnvironmentObject var state: ContentViewState
    
    var body: some View {
        HStack {
            Spacer()
            getTextField(
                $state.blueCenterLeft,
                state,
                padding: .trailing
            )
            getTextField(
                $state.red,
                state,
                color: .hexagonRed,
                padding: .trailing
            )
            getTextField(
                $state.blueCenterRight,
                state,
                padding: .leading
            )
            Spacer()
        }
        .padding(.top, -10)
    }
}

struct LowerLetters: View {
    @EnvironmentObject var state: ContentViewState
    
    var body: some View {
        HStack {
            Spacer()
            getTextField(
                $state.blueLowerLeft,
                state,
                padding: .trailing
            )
            getTextField(
                $state.blueLowerRight,
                state,
                padding: .leading
            )
            Spacer()
        }
        .padding(.top, -10)
    }
}

struct Warning: View {
    @EnvironmentObject var state: ContentViewState
    
    var body: some View {
        if !state.warningMessage.isEmpty {
            Text(state.warningMessage)
                .foregroundColor(.hexagonRed)
                .padding()
        }
    }
}

struct ResultsButton: View {
    @EnvironmentObject var state: ContentViewState
    
    @Environment(\.colorScheme) private var colorScheme
    
    var body: some View {
        Button(action: {
            hideKeyboard()
            state.viewModel.onClickButton(
                red: state.red,
                blue: state.setBlue()
            )
        }) {
            Text("Introdueix")
                .foregroundColor(
                    state.isButtonEnabled
                    ? colorScheme == .dark ? .white : .black
                    : .gray
                )
                .padding(15)
                .overlay(
                    RoundedRectangle(cornerRadius: 25)
                        .stroke(Color.gray, lineWidth: 1)
                )
        }
        .disabled(state.isButtonEnabled == false)
        .padding()
    }
}

struct Words: View {
    @EnvironmentObject var state: ContentViewState
    
    var body: some View {
        if !state.words.isEmpty {
            Group {
                Text("Solució del joc amb \(state.wordAmount) paraules possibles: ")
                + Text(state.words)
                    .bold()
            }
            .padding()
            .multilineTextAlignment(.center)
        }
    }
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
        ContentView()
	}
}
