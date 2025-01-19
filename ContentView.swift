import SwiftUI
import PythonKit

struct ContentView: View {
    @State private var query: String = ""
    @State private var results: [String] = []

    var body: some View {
        VStack {
            TextField("Enter search query", text: $query)
                .textFieldStyle(RoundedBorderTextFieldStyle())
                .padding()
            
            Button(action: performSearch) {
                Text("Search")
                    .padding()
                    .background(Color.blue)
                    .foregroundColor(.white)
                    .cornerRadius(5)
            }
            
            List(results, id: \.self) { result in
                Text(result)
            }
        }
        .padding()
    }

    func performSearch() {
        guard !query.isEmpty else { return }

        let sys = Python.import("sys")
        sys.path.append(Bundle.main.bundlePath)

        if let searchModule = try? Python.import("search") {
            let pythonResults = searchModule.get_search_results(query)
            results = pythonResults.map { $0.description }
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
