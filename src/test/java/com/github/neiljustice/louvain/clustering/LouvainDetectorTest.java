
/* MIT License

Copyright (c) 2018 Neil Justice

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE. */

package com.github.neiljustice.louvain.clustering;

import com.github.neiljustice.louvain.graph.*;
import java.util.*;
import java.io.*;
import java.net.URL;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

public class LouvainDetectorTest {

  @Test
  public void checkArxivGraphMod() {
    Graph g = new GraphBuilder().fromFile(getFile("graphs/arxiv.txt"));
    LouvainDetector ld = new LouvainDetector(g);
    ld.run();
    assertTrue(ld.modularity() > 0.81);
  }

  @Test
  public void checkCavemanGraph() {
    Graph g = new GraphBuilder().fromFile(getFile("graphs/connected-caveman-graph.csv"));
    LouvainDetector ld = new LouvainDetector(g);
    List<int[]> res = ld.run();
    assertEquals(res.size(), 1);
    assertEquals(g.partitioning().numComms(), 6);
    assertTrue(ld.modularity() > 0.7);
  }
  
  private File getFile(String filename) {
    URL url = Thread.currentThread().getContextClassLoader().getResource(filename);
    return new File(url.getPath());    
  }
}
