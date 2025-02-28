import SwiftUI

struct ContentView: View {
    @State private var taskText = ""
    @State private var tasks: [(String, Bool)] = []

    var body: some View {
        VStack {
            HStack {
                TextField("Enter task...", text: $taskText)
                    .textFieldStyle(RoundedBorderTextFieldStyle())
                    .padding()

                Button(action: {
                    if !taskText.isEmpty {
                        tasks.append((taskText, false))
                        taskText = ""
                    }
                }) {
                    Text("Add")
                        .padding()
                        .background(Color.blue)
                        .foregroundColor(.white)
                        .cornerRadius(8)
                }
            }
            List {
                ForEach(tasks.indices, id: \.self) { index in
                    HStack {
                        Text(tasks[index].0)
                        Spacer()
                        Toggle("", isOn: $tasks[index].1)
                            .labelsHidden()
                    }
                }
            }
        }
        .padding()
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
