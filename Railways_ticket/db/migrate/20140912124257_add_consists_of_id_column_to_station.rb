class AddConsistsOfIdColumnToStation < ActiveRecord::Migration
  def change
    add_column :stations, :consists_of_id, :integer
  end
end
