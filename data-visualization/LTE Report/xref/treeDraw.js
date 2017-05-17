// Code goes here
(function() {
  var width = window.innerWidth,
    height = window.innerHeight,
    radius = window.innerHeight * 0.5;

  var burrow = function(table) {
    // create nested object
    var obj = {};
    table.forEach(function(row) {
      // start at root
      var layer = obj;

      // create children as nested objects
      row.taxonomy.forEach(function(key) {
        layer[key] = key in layer ? layer[key] : {};
        layer = layer[key];
      });
    });

    // recursively create children array
    var descend = function(obj, depth) {
      var arr = [];
      var depth = depth || 0;
      for (var k in obj) {
        var child = {
          name: k,
          depth: depth,
          children: descend(obj[k], depth + 1)
        };
        arr.push(child);
      }
      return arr;
    };

    // use descend to create nested children arrys
    return {
      name: "root",
      children: descend(obj, 1),
      depth: 0
    }
  };

  var tree = d3.tree()
    .size([360, radius - 90])
    .separation(function(a, b) {
      return (a.parent == b.parent ? 1 : 2) / a.depth;
    });

  var svg = d3.select("body").append("svg")
    .attr("width", width)
    .attr("height", height)
    .append("g")
    .attr("transform", "translate(" + (width / 2) + "," + (height / 2) + ")");

  d3.tsv("treedata.tsv", function(error, data) {
    if (error) throw error;

    // splitting keys into an array is required before passing data into burrow
    data.forEach(function(row) {
      row.taxonomy = row.file.split("/");
    });

    var root = d3.hierarchy(burrow(data));

    tree(root);

    var link = svg.selectAll(".link")
      .data(root.descendants().slice(1))
      .enter().append("path")
      .attr("class", "link")
      .attr("d", function(d) {
        return "M" + project(d.x, d.y) + "C" + project(d.x, (d.y + d.parent.y) / 2) + " " + project(d.parent.x, (d.y + d.parent.y) / 2) + " " + project(d.parent.x, d.parent.y);
      });

    var node = svg.selectAll(".node")
      .data(root.descendants())
      .enter().append("g")
      .attr("class", function(d) {
        return "node" + (d.children ? " node--internal" : " node--leaf");
      })
      .attr("transform", function(d) {
        return "translate(" + project(d.x, d.y) + ")";
      });

    node.append("circle")
      .attr("r", 2.5);

    node.append("text")
      .attr("dy", ".31em")
      .attr("x", function(d) {
        return d.x < 180 === !d.children ? 6 : -6;
      })
      .style("text-anchor", function(d) {
        return d.x < 180 === !d.children ? "start" : "end";
      })
      .attr("transform", function(d) {
        return "rotate(" + (d.x < 180 ? d.x - 90 : d.x + 90) + ")";
      })
      .text(function(d) {
        return d.data.name;
      });
  });

  function project(x, y) {
    var angle = (x - 90) / 180 * Math.PI,
      radius = y;
    return [radius * Math.cos(angle), radius * Math.sin(angle)];
  }
})();