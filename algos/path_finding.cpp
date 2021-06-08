// dynamic programming method
#include <bits/stdc++.h>
using namespace std;

const int oo = 1e9 + 7, MASK = (1 << 20) + 5, N = 20 + 5;

int n;
string name[N];
map<string, int> id;
int dist[N][N], trace[MASK][N], dp[MASK][N];
vector<int> gym;

void input() {
	// load the places
	// the warehouse will be number 0
	scanf("%d ", &n); // number of gyms
	for (int i = 1; i <= n; ++ i) {
		getline(cin, name[i]);
		// cerr << name[i] << '\n';
		id[name[i]] = i;
	}
	// load the distances (in meters)
	for (int i = 0; i <= n; ++i) {
		for (int j = 0; j <= n; ++ j) {
			scanf("%d ", &dist[i][j]);
		}
	}
;	// load the need-to-gym gyms
	// this will be continuously changed
	string str;
	while (getline(cin, str)) {
		if (str == "") break;
		gym.push_back(id[str]);
		// cerr << id[str] << '\n';
	}
	// cerr << "ok\n";
}

void solve() {
	for (int mask = 0; mask < (1 << gym.size()); ++ mask) {
		for (int i = 0; i < n; ++ i) {
			dp[mask][i] = oo;
		}
	}
	for (int mask = 1; mask < (1 << gym.size()); mask ++) {
		for (int i = 0; i < gym.size(); ++ i) {
			// cerr << mask << ' ' << i << '\n';
			if (mask & (1 << i) == 0) continue;
			if (__builtin_popcount(mask) == 1) {
				dp[mask][i] = dist[0][gym[i]];
				continue;
			}
			for (int j = 0; j < gym.size(); ++j) {
				if (i == j || (mask >> j & 1) == 0) continue;
				if (dp[mask ^ (1 << i)][j] + dist[gym[i]][gym[j]] < dp[mask][i]) {
					dp[mask][i] = dp[mask ^ (1 << i)][j] + dist[gym[i]][gym[j]];
					trace[mask][i] = j;
				}
			}
		}
	}
}

void tracing(int mask, int u) {
	// cerr << mask << ' ' << u << '\n';
	char bull = 201; 
	if (mask == 0) {
		printf(" %c Warehouse\n", bull);
		return;
	}
	tracing(mask ^ (1 << u), trace[mask][u]);
	printf(" %c %s\n", bull, name[gym[u]].c_str());
}

void answer() {
	int res = oo, last = -1;
	for (int i = 0; i < n; ++ i) {
		if (dp[(1 << gym.size()) - 1][i] < res) {
			res = dp[(1 << gym.size()) - 1][i];
			last = i;
		}
	}
	// cerr << "last = " << last << '\n';
	// cerr << trace[15][4]
	printf("The optimizal shipper's route: \n");
	tracing((1 << gym.size()) - 1, last);
	printf("-----------------------------------------\n");
	printf("Total distance: %d(m)\n", res);
}

int main() {
	freopen("inputJava.txt", "r", stdin);
	// freopen("outputJava.txt", "w", stdout);
	input();
	solve();
	answer();
}